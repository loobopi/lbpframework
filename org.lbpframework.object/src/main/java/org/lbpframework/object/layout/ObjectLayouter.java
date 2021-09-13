package org.lbpframework.object.layout;

import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.FieldData;
import org.openjdk.jol.info.FieldLayout;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.util.MathUtil;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ObjectLayouter {
    public ObjectLayouter() {
    }

    public ObjectLayout layout(ClassData data) {
        VirtualMachine vm = VM.current();
        long instanceSize;
        if (data.isArray()) {
            int base = vm.arrayBaseOffset(data.arrayComponentType());
            int scale = vm.arrayIndexScale(data.arrayComponentType());
            instanceSize = MathUtil.align((long)base + data.arrayLength() * (long)scale, vm.objectAlignment());
            SortedSet<FieldLayout> result = new TreeSet();
            result.add(new FieldLayout(FieldData.create(data.arrayClass(), "<elements>", data.arrayComponentType()), (long)base, (long)scale * data.arrayLength()));
            return new ObjectLayout(data, result, vm.arrayHeaderSize(), instanceSize, false);
        } else {
            Collection<FieldData> fields = data.fields();
            SortedSet<FieldLayout> result = new TreeSet();
            Iterator var5 = fields.iterator();

            while(var5.hasNext()) {
                FieldData f = (FieldData)var5.next();
                result.add(new FieldLayout(f, f.vmOffset(), vm.sizeOfField(f.typeClass())));
            }

            if (result.isEmpty()) {
                instanceSize = (long)vm.objectHeaderSize();
            } else {
                FieldLayout f = (FieldLayout)result.last();
                instanceSize = f.offset() + f.size();
            }

            instanceSize = MathUtil.align(instanceSize, vm.objectAlignment());
            return new ObjectLayout(data, result, vm.objectHeaderSize(), instanceSize, true);
        }
    }

    public String toString() {
        return "Current VM Layout";
    }

}

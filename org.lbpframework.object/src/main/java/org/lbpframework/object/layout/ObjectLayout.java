package org.lbpframework.object.layout;

import org.lbpframework.ReflectUtils;
import org.lbpframework.logging.LoggerUtil;
import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.FieldData;
import org.openjdk.jol.info.FieldLayout;
import org.openjdk.jol.layouters.CurrentLayouter;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.util.ClassUtils;
import org.openjdk.jol.util.ObjectUtils;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.SortedSet;

public class ObjectLayout {

    private  ClassData classData;
    private  SortedSet<FieldLayout> fields;
    private  int headerSize;
    private  long size;

    public ClassData getClassData(){
        return this.classData;
    }


    public static ObjectLayout parseClass(Class<?> klass) {
        return parseClass(klass, new ObjectLayouter());
    }

    public static ObjectLayout parseClass(Class<?> klass, ObjectLayouter layouter) {
        return (ObjectLayout)layouter.layout(ClassData.parseClass(klass));
    }

    public static ObjectLayout parseInstance(Object instance) {
        return parseInstance(instance, new ObjectLayouter());
    }

    public static ObjectLayout parseInstance(Object instance, ObjectLayouter layouter) {
        return (ObjectLayout)layouter.layout(ClassData.parseInstance(instance));
    }

    public ObjectLayout(ClassData classData, SortedSet<FieldLayout> fields, int headerSize, long instanceSize, boolean check) {
        this.classData = classData;
        this.fields = fields;
        this.headerSize = headerSize;
        this.size = instanceSize;
        if (check) {
            this.checkInvariants();
        }

    }

    private void checkInvariants() {
        FieldLayout lastField = null;

        FieldLayout f;
        for(Iterator var2 = this.fields.iterator(); var2.hasNext(); lastField = f) {
            f = (FieldLayout)var2.next();
            if (f.offset() % f.size() != 0L) {
                throw new IllegalStateException("Field " + f + " is not aligned");
            }

            if (f.offset() + f.size() > this.instanceSize()) {
                throw new IllegalStateException("Field " + f + " is overflowing the object of size " + this.instanceSize());
            }

            if (lastField != null && f.offset() < lastField.offset() + lastField.size()) {
                throw new IllegalStateException("Field " + f + " overlaps with the previous field " + lastField);
            }
        }

    }

    public SortedSet<FieldLayout> fields() {
        return this.fields;
    }

    public long instanceSize() {
        return this.size;
    }

    public int headerSize() {
        return this.headerSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this.fields != null){
            Iterator var2 = this.fields().iterator();

            while(var2.hasNext()) {
                FieldLayout f = (FieldLayout)var2.next();
                if(f != null){
                    sb.append(f).append("\n");
                }
            }

            sb.append("size = ").append(this.size).append("\n");
        }
        return sb.toString();
    }

    public String toPrintable() {
        return this.toPrintable(this.classData.instance());
    }

    public String toPrintable(Object instance) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String MSG_GAP = "(alignment/padding gap)";
        String MSG_NEXT_GAP = "((填充数据)loss due to the next object alignment)";
        int maxTypeLen = "TYPE".length();

        FieldLayout f;
        for(Iterator var7 = this.fields().iterator(); var7.hasNext(); maxTypeLen = Math.max(f.typeClass().length(), maxTypeLen)) {
            f = (FieldLayout)var7.next();
        }

        maxTypeLen += 2;
        int maxDescrLen = Math.max(MSG_GAP.length(), MSG_NEXT_GAP.length());

        for(Iterator var19 = this.fields().iterator(); var19.hasNext(); maxDescrLen = Math.max(f.shortFieldName().length(), maxDescrLen)) {
            f = (FieldLayout)var19.next();
        }

        maxDescrLen += 2;
        if (instance != null) {
            try {
                Class<?> klass = ClassUtils.loadClass(this.classData.name());
                if (!klass.isAssignableFrom(instance.getClass())) {
                    throw new IllegalArgumentException("Passed instance type " + instance.getClass() + " is not assignable from " + klass + ".");
                }
            } catch (ClassNotFoundException var17) {
                throw new IllegalArgumentException("Class is not found: " + this.classData.name() + ".");
            }
        }

        pw.println( "对象类型:(" + this.classData.name() + ")"  );
        pw.printf(" %6s %5s %" + maxTypeLen + "s %-" + maxDescrLen + "s %s%n", "OFFSET", "SIZE(字节)", "TYPE", "DESCRIPTION", "VALUE");
        if (instance != null) {
            VirtualMachine vm = VM.current();

            for(long off = 0L; off < (long)this.headerSize(); off += 4L) {
                int word = vm.getInt(instance, off);
                pw.printf(" %6d %5d %" + maxTypeLen + "s %-" + maxDescrLen + "s %s%n", off, 4, "", "(对象头)", toHex(word >> 0 & 255) + " " + toHex(word >> 8 & 255) + " " + toHex(word >> 16 & 255) + " " + toHex(word >> 24 & 255) + " (" + toBinary(word >> 0 & 255) + " " + toBinary(word >> 8 & 255) + " " + toBinary(word >> 16 & 255) + " " + toBinary(word >> 24 & 255) + ") (" + word + ")");
            }
        } else {
            pw.printf(" %6d %5d %" + maxTypeLen + "s %-" + maxDescrLen + "s %s%n", 0, this.headerSize(), "", "(对象头)", "N/A");
        }

        long nextFree = (long)this.headerSize();
        long interLoss = 0L;
        long exterLoss = 0L;

        for(Iterator var14 = this.fields().iterator(); var14.hasNext(); nextFree = f.offset() + f.size()) {
            f = (FieldLayout)var14.next();
            if (f.offset() > nextFree) {
                pw.printf(" %6d %5d %" + maxTypeLen + "s %-" + maxDescrLen + "s%n", nextFree, f.offset() - nextFree, "", MSG_GAP);
                interLoss += f.offset() - nextFree;
            }
            /*Field fi = f.data().refField();*/
            Field fi = null;

            {

                FieldData fieldData = (FieldData)ReflectUtils.getFieldValue(f,"f");
                fi = fieldData.refField();
            }

            pw.printf(" %6d %5d %" + maxTypeLen + "s %-" + maxDescrLen + "s %s%n", f.offset(), f.size(), f.typeClass(), f.shortFieldName(), instance != null && fi != null ? ObjectUtils.safeToString(ObjectUtils.value(instance, fi)) : "N/A");
        }

        long sizeOf = instance != null ? VM.current().sizeOf(instance) : this.instanceSize();
        if (sizeOf != nextFree) {
            exterLoss = sizeOf - nextFree;
            pw.printf(" %6d %5s %" + maxTypeLen + "s %s%n", nextFree, exterLoss, "", MSG_NEXT_GAP);
        }

        pw.printf("Instance size(字节): %d bytes%n", sizeOf);
        pw.printf("Space losses: %d bytes internal + %d bytes external = %d bytes total", interLoss, exterLoss, interLoss + exterLoss);
        pw.close();
        return sw.toString();
    }

    public static String toBinary(int x) {
        String s = Integer.toBinaryString(x);
        int deficit = 8 - s.length();

        for(int c = 0; c < deficit; ++c) {
            s = "0" + s;
        }

        return s;
    }

    private static String toHex(int x) {
        String s = Integer.toHexString(x);
        int deficit = 2 - s.length();

        for(int c = 0; c < deficit; ++c) {
            s = "0" + s;
        }

        return s;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ClassLayout that = (ClassLayout)o;
            {
                Integer headSize = (Integer)ReflectUtils.getFieldValue(that,"headSize");
                Integer size = (Integer)ReflectUtils.getFieldValue(that,"size");
                Field thatField = (Field)ReflectUtils.getFieldValue(that,"fields");
                if (this.headerSize != headSize) {
                    return false;
                } else {
                    return this.size != size ? false : this.fields.equals(thatField);
                }
            }
            /*if (this.headerSize != that.headerSize) {
                return false;
            } else {
                return this.size != that.size ? false : this.fields.equals(that.fields);
            }*/
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.fields.hashCode();
        result = 31 * result + this.headerSize;
        result = 31 * result + (int)(this.size ^ this.size >>> 32);
        return result;
    }
}

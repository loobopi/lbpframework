package org.lbpframework.web.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Comparable {

    String name;
    String author;
    double price;


    @Override
    public int compareTo(Object o) {
         return this.price>((Book) o).price?1:-1;
    }
}

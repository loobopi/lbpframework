package org.lbpframework.object.resolver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对象信息定义
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDefinition {

    /**
     * 对象头信息
     */
    ObjectHeader header;



}

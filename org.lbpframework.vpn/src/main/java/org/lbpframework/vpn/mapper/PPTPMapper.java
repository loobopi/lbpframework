package org.lbpframework.vpn.mapper;

import org.lbpframework.vpn.processor.PPTPReply;
import org.lbpframework.vpn.processor.PPTPReplyWrapper;
import org.lbpframework.vpn.processor.PPTPRequest;
import org.lbpframework.vpn.processor.PPTPRequestWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PPTPMapper {

    PPTPMapper INSTANCE = Mappers.getMapper(PPTPMapper.class);

    @Mappings(value={@Mapping(target = "content" , ignore = true)})
    public PPTPReply toPPTPStartControlConnectionReply(PPTPRequest request);

    public PPTPReplyWrapper toPPTPStartControlConnectionReplyWrapper(PPTPRequestWrapper requestWrapper);

}

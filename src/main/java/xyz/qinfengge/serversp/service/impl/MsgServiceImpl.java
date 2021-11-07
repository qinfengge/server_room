package xyz.qinfengge.serversp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.qinfengge.serversp.entity.Msg;
import xyz.qinfengge.serversp.service.MsgService;
import xyz.qinfengge.serversp.mapper.MsgMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg>
    implements MsgService{

}





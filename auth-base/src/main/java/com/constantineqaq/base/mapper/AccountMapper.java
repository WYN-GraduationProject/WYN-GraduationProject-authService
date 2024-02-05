package com.constantineqaq.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.constantineqaq.base.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}

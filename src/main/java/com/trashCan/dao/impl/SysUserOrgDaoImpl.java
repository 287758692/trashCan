package com.trashCan.dao.impl;

import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysUserOrgDao;

@Repository("sysUserOrgDao")
public class SysUserOrgDaoImpl<SysUserOrg> extends BaseDaoImpl<SysUserOrg> implements SysUserOrgDao<SysUserOrg>{

}
package com.iedu.service.utils;

import com.iedu.common.core.domain.entity.SysUser;

public class UserHolder {
    private static final ThreadLocal<SysUser> HOLDER = new ThreadLocal<>();

    public static void set(SysUser user) {
        HOLDER.set(user);
    }

    public static SysUser get() {
        return HOLDER.get();
    }

    public static void remove() {
        HOLDER.remove();
    }

}

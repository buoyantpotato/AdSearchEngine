package com.adsearch.dao;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class MemcachedManager {

    private static final int EXP_TIME = 60000;

    private String memcachedServerHost;
    private int memcachedPort;

    public MemcachedManager(String memcachedServerHost, int memcachedPort) {
        this.memcachedServerHost = memcachedServerHost;
        this.memcachedPort = memcachedPort;
    }

    public void addData(String key, String val) {
        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(memcachedServerHost, memcachedPort));
            cache.add(key, EXP_TIME, val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringData(String key) {
        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(memcachedServerHost, memcachedPort));
            Object val = cache.get(key);
            if (val instanceof String) {
                return (String) val;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void addListOfStringData(String key, String val) {
        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(memcachedServerHost, memcachedPort));
            Object list = cache.get(key);
            if (list instanceof Set) {
                ((Set) list).add(val);
                cache.set(key, EXP_TIME, list);
            } else {
                list = new HashSet<String>();
                ((Set) list).add(val);
                cache.set(key, EXP_TIME, list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> getListOfStringData(String key) {
        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(memcachedServerHost, memcachedPort));
            Object list = cache.get(key);
            if (list instanceof Set) {
                return (HashSet<String>) list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

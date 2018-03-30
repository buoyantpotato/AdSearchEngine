package com.adsearch;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Memcached {
    private static final String MEMCACHE_SERVER = "127.0.0.1";
    private static final int MEMCACHE_PORT = 11211;
    private static final int EXP_TIME = 60000;

    public void addData(String key, String val) {

        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(MEMCACHE_SERVER, MEMCACHE_PORT));
            cache.add(key, EXP_TIME, val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringData(String key) {
        try {
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(MEMCACHE_SERVER, MEMCACHE_PORT));
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
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(MEMCACHE_SERVER, MEMCACHE_PORT));
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
            MemcachedClient cache = new MemcachedClient(new InetSocketAddress(MEMCACHE_SERVER, MEMCACHE_PORT));
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

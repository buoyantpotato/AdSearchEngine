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
    private MemcachedClient memcachedClient = null;

    public MemcachedManager(String memcachedServerHost, int memcachedPort) {
        this.memcachedServerHost = memcachedServerHost;
        this.memcachedPort = memcachedPort;
    }

    private MemcachedClient getClient() throws IOException {
        System.out.println("INFO: The system is claiming a new memcached client for indexing...");
        return new MemcachedClient(new InetSocketAddress(memcachedServerHost, memcachedPort));
    }

    public boolean addIdToSet(String key, Long val) {
        try {
            if (this.memcachedClient == null) {
                this.memcachedClient = getClient();
            }

            Object list = this.memcachedClient.get(key);
            boolean res = false;
            if (list instanceof Set) {
                res = ((Set<Long>) list).add(val);
                this.memcachedClient.set(key, EXP_TIME, list);
            } else {
                list = new HashSet<Long>();
                res = ((Set<Long>) list).add(val);
                this.memcachedClient.set(key, EXP_TIME, list);
            }

            if (res) {
                System.out.println("Cache is added to Memcached: " + key + " <- " + val);
            } else {
                System.out.println("Cache already exited: " + key + " <- " + val);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Problems in MemcachedManager.addIdToSet()");
            return false;
        }

        return true;
    }

    public Set<Long> getIdListByToken(String key) {
        try {
            if (this.memcachedClient == null) {
                this.memcachedClient = getClient();
            }

            Object list = this.memcachedClient.get(key);
            if (list instanceof Set) {
                return (Set<Long>) list;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Problems in MemcachedManager.getIdListByToken()");
        }

        return null;
    }

}

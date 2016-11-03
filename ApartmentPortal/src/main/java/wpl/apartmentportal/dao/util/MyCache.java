package wpl.apartmentportal.dao.util;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
 
public class MyCache {
    private static final String NAMESPACE= "SACHARYA:5d41402abc4b2a76b9719d91101";
    private static MyCache instance = null;
    private static MemcachedClient m = null;
     
    private MyCache() {
    
        try {
			m =  new MemcachedClient(
			                 new BinaryConnectionFactory(),
			     AddrUtil.getAddresses("127.0.0.1:11211"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
                
    }
     
    public static synchronized MyCache getInstance() {
        System.out.println("Instance: " + instance);
        if(instance == null) {
            System.out.println("Creating a new instance");
            instance = new MyCache();
         }
         return instance;
    }
     
    public void set(String key, final Object o) {
        getCache().set(NAMESPACE + key, 10, o);
    }
     
    public Object get(String key) {
        Object o = getCache().get(NAMESPACE + key);
        if(o == null) {
            System.out.println("Cache MISS for KEY: " + key);
        } else {
            System.out.println("Cache HIT for KEY: " + key);
        }
        return o;
    }
     
    public Object delete(String key) {
        return getCache().delete(NAMESPACE + key);  
    }
     
     
    public MemcachedClient getCache() {
        
        return m;
    }
}
package com.x.interfaceRedirect;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheConfig {
  
  private final static Logger LOG = Logger.getLogger(CacheConfig.class);
  
  private static CacheConfig instance = new CacheConfig();
  private static LoadingCache<String, String> cache ;
  private CacheConfig(){
  }
  static{
    cache = CacheBuilder.newBuilder().maximumSize(20)// 设置大小，条目数
        .expireAfterWrite(5, TimeUnit.SECONDS)// 设置失效时间，创建时间
        .expireAfterAccess(20, TimeUnit.HOURS) // 设置时效时间，最后一次被访问
        .removalListener(new RemovalListener<String, String>() { // 移除缓存的监听器
              public void onRemoval(RemovalNotification<String, String> notification) {
              }
            }).build(new CacheLoader<String, String>() { // 通过回调加载缓存
              @Override
              public String load(String name) throws Exception {
                DaoConfig daoConfig = new DaoConfig();
                LOG.debug("load work:"+name);
                return daoConfig.getTargetUrlByTitle(name);
              }
            });
  }
  
  public static CacheConfig getInstance(){
    return instance;
  }
  
  public static void main(String[] args) throws Exception {
    CacheConfig cache = new CacheConfig();
    cache.getNameLoadingCache("android");
  }

  public String getNameLoadingCache(String name) throws Exception {
    return cache.get(name);
    // cache.invalidateAll();
  }

}

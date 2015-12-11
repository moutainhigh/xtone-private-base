package org.common.util;

import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class GenerateIdService {

  private static GenerateIdService instance = new GenerateIdService();

  public static GenerateIdService getInstance() {
    return instance;
  }

  private static final Map<String, Long> currentTitleMap = new ConcurrentHashMap<String, Long>();
  static final Map<String, GenerateIdCell> currentTitleUsedMap = new ConcurrentHashMap<String, GenerateIdCell>();
  private static int tempRnd = 0;
  private static Random rnd = new Random();
  private static int count = 0;
  public static final long SERVER_ADD_MS_SPEND_LIMIT = 1000000L;
  public static final int MS_SPEND_LIMIT = 10000;
  private static final int MS_SPEND_LIMIT_RND = MS_SPEND_LIMIT - 1;

  public static final String EXCEPTION_SERVER_ID_INVALID = "Server id invalid , it must > 0 and <"
      + SERVER_ADD_MS_SPEND_LIMIT / MS_SPEND_LIMIT;
  public static final String EXCEPTION_STEP_INVALID = "Step id invalid , it must > 0 and <" + MS_SPEND_LIMIT;
  public static final String EXCEPTION_OVERLOAD = "overload";
  
  
  /**
   *   with default step 1
   * @throws Exception 
   * */
  static public synchronized long generateNew(int serverId, String key) {
    return generateNew(serverId, key, 1);
  }

  /**
   * @param serverId
   *          <=99 (SERVER_ADD_MS_SPEND_LIMIT/MS_SPEND_LIMIT) enable
   *          MS_SPEND_LIMIT in a ms random to work perhaps when server time
   *          adjust
   * @param key
   *          as database table name
   * @param step
   *          how many id apply .
   * @throws Exception 
   * 
   * */
  static public synchronized long generateNew(int serverId, String key, int step) {

    checkParameter(serverId, key, step);

    long result = 0L;
    long current = System.currentTimeMillis();
    // +serverId*1000
    if (currentTitleMap.containsKey(key)) {
      if (currentTitleMap.get(key).longValue() == current) {
        currentTitleUsedMap.get(key).setCurrent(currentTitleUsedMap.get(key).getCurrent()+step);
        if (currentTitleUsedMap.get(key).getCurrent() > MS_SPEND_LIMIT_RND) {
          if (currentTitleUsedMap.get(key).getCurrent() > (MS_SPEND_LIMIT_RND+MS_SPEND_LIMIT)){
            throw new IllegalArgumentException(EXCEPTION_OVERLOAD);
          }else{
            result = procKey(serverId, current, currentTitleUsedMap.get(key).getCurrent()-MS_SPEND_LIMIT);
          }
        } else {
          result = procKey(serverId, current, currentTitleUsedMap.get(key).getCurrent());
        }
      } else {
        currentTitleMap.put(key, current);
        tempRnd = rnd.nextInt(MS_SPEND_LIMIT_RND);
        ;
        currentTitleUsedMap.get(key).setBegin(tempRnd);
        currentTitleUsedMap.get(key).setCurrent(tempRnd);
        result = procKey(serverId, current, tempRnd);
      }
    } else {
      tempRnd = rnd.nextInt(MS_SPEND_LIMIT_RND);
      GenerateIdCell GenerateIdCell = new GenerateIdCell(tempRnd,tempRnd);
      
      result = procKey(serverId, current, tempRnd);
      currentTitleMap.put(key, current);
      currentTitleUsedMap.put(key, GenerateIdCell);
    }
    return result;
  }

  private static void checkParameter(int serverId, String key, int step) {
    if (serverId >= SERVER_ADD_MS_SPEND_LIMIT / MS_SPEND_LIMIT) {
      throw new IllegalArgumentException(EXCEPTION_SERVER_ID_INVALID);
    } else if (serverId < 1) {
      throw new IllegalArgumentException(EXCEPTION_SERVER_ID_INVALID);
    } else if (step >= MS_SPEND_LIMIT) {
      throw new IllegalArgumentException(EXCEPTION_STEP_INVALID);
    } else if (step < 1) {
      throw new IllegalArgumentException(EXCEPTION_STEP_INVALID);
    }
  }

  private static long procKey(int serverId, long current, Integer tempRnd) {
    long result;
    result = current * SERVER_ADD_MS_SPEND_LIMIT + serverId * MS_SPEND_LIMIT + tempRnd;
    return result;
  }
  
  public static void main(String[] args){
    int validServerId = (int) GenerateIdService.SERVER_ADD_MS_SPEND_LIMIT / GenerateIdService.MS_SPEND_LIMIT - 1;
    for (int i=1;i<1000;i++){
      System.out.println(GenerateIdService.generateNew(validServerId, ""));  
    }
    System.out.println(GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1));
    System.out.println(GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1));
    System.out.println(GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1));
    System.out.println(GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1));
    System.out.println(GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1));
    GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1);
    GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1);
    GenerateIdService.generateNew(validServerId, "", GenerateIdService.MS_SPEND_LIMIT-1);
  }

  /*
   * can generate continue static public synchronized long generateNew(int
   * serverId, String key) { long result = 0L; long current =
   * System.currentTimeMillis(); // +serverId*1000 if
   * (currentTitleMap.containsKey(key)) { if
   * (currentTitleMap.get(key).longValue() == current) {
   * currentTitleUsedMap.put(key,currentTitleUsedMap.get(key)+1); result =
   * current * SERVER_ADD_MS_SPEND_LIMIT + serverId * MS_SPEND_LIMIT +
   * currentTitleUsedMap.get(key) ; } else { currentTitleMap.put(key, current);
   * currentTitleUsedMap.put(key,0); result = current *
   * SERVER_ADD_MS_SPEND_LIMIT + serverId * MS_SPEND_LIMIT ; } } else { result =
   * current * SERVER_ADD_MS_SPEND_LIMIT + serverId * MS_SPEND_LIMIT ;
   * currentTitleMap.put(key, current); currentTitleUsedMap.put(key, 0); }
   * return result; }
   */
}
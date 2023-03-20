package vm;

import java.util.HashMap;

public class VirtualMachine {
  /** メモリ */
  private static long[] memory = new long[1024];
  /** レジスタ */
  private static int[] register = new int[8];

  /** レジスタ名に対応した番号 */
  private static HashMap<String, Integer> registerMap = new HashMap<>();

  /** 変数とメモリアドレスを対応させるhashmap */
  private static HashMap<String, Integer> valueName = new HashMap<>();
  static {
    registerMap.put("EAX", 0x00);
    registerMap.put("EBX", 0x01);
    registerMap.put("ECX", 0x02);
    registerMap.put("EDX", 0x03);
    registerMap.put("ESI", 0x04);
    registerMap.put("EDI", 0x05);
    registerMap.put("EBP", 0x06);
    registerMap.put("ESP", 0x07);
  }

  private static String[] registerName = { "EAX", "EBX", "ECX", "EDX", "ESI", "EDI", "EBP", "ESP" };

  /**
   * レジスタ名を指定してレジスタ番号を取得する
   * 
   * @param name レジスタ名
   * @return レジスタ番号
   */
  public static int getRegisterNumber(String name) {
    return registerMap.get(name);
  }

  public static String getRegisterName(byte b) {
    return registerName[b];
  }

  /**
   * レジスタが存在するか確認する
   * 
   * @param name レジスタ名
   * @return true false
   */
  public static boolean isRegister(String name) {
    return registerMap.get(name) != null;
  }

  /**
   * メモリアドレスを指定してメモリに書き込む
   * 
   * @param ptr メモリアドレス
   * @param l   値
   */
  public static void writeMemory(int ptr, long l) {
    memory[ptr] = l;
  }

  /**
   * メモリを読み取る
   * 
   * @param ptr メモリアドレス
   * @return 値
   */
  public static long readMemory(int ptr) {
    return memory[ptr];
  }

  /**
   * 空いているメモリに書き込む
   * 
   * @param l 値
   */
  public static void writeMemory(long l) {
    int i = 0;
    while (memory[i] != 0) {
      i++;
    }
    memory[i] = l;
  }

  /**
   * メモリサイズを変更する
   * 
   * @param size メモリサイズ
   */
  public static void resize(int size) {
    long newMemory[] = new long[size];
    for (int i = 0; i < memory.length; i++) {
      newMemory[i] = memory[i];
    }
    memory = newMemory;
  }

  /**
   * レジスタの値を変更する
   * 
   * @param number レジスタ番号
   * @param l      値
   */
  public static void setRegister(int number, int l) {
    register[number] = l;
  }

  /**
   * レジスタの値を取得する
   * 
   * @param number レジスタ番号
   * @return 値
   */
  public static int getRegister(int number) {
    return register[number];
  }

  /** 空いているメモリアドレスを探す */
  public static int getFreeMemoryAddress() {
    for (int i = 0; i < memory.length; i++) {
      if (memory[i] == 0x00) {
        return i;
      }
    }
    return -1;
  }

  /** 変数を追加する */
  public static void addValue(String name, long value) {
    valueName.put(name, getFreeMemoryAddress());
    memory[getFreeMemoryAddress()] = value;
  }

  /**
   * 変数がさすアドレスを取得する
   * 
   * @param name 変数名
   * @return Address
   */
  public static int getValueAddr(String name) {
    return valueName.get(name);
  }
}

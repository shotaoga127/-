package vm;

/** 命令番号 */
public class Command {
  public static final byte MOV = (byte) 0x80;
  public static final byte ADD = (byte) 0x90;
  public static final byte SUB = (byte) 0x91;
  public static final byte MUL = (byte) 0x92;
  public static final byte DIV = (byte) 0x93;
  public static final byte AND = (byte) 0x94;
  public static final byte OR = (byte) 0x95;
  public static final byte NOT = (byte) 0x96;
  public static final byte XOR = (byte) 0x97;
  public static final byte INC = (byte) 0x98;
  public static final byte DEC = (byte) 0x99;
  public static final byte SHL = (byte) 0x64;
  public static final byte SHR = (byte) 0x65;

  // 変数と数値の初期化
  public static final byte DW = (byte) 0x60;
  // コンパイラに変数名をAddressに変換する命令
  public static final byte COM_ADD = (byte) 0x71;

  public static final byte STACK_PUSH = (byte) 0x50;
  public static final byte STACK_POP = (byte) 0x51;

  // システムコール番号はvm.syscall.Syscallsに定義
  public static final byte SYSCALL = (byte) 0x70;

  public static final byte TYPE_NUMBER = (byte) 0xa0;
  public static final byte TYPE_MEMORY = (byte) 0xa1;
  public static final byte TYPE_REGISTER = (byte) 0xa2;
  public static final byte TYPE_VARIABLE = (byte) 0xa4;
}

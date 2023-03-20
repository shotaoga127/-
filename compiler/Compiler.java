package compiler;

import java.nio.file.*;
import vm.*;
import vm.exception.*;

public class Compiler {
  static byte[] bytes = new byte[1024 * 1024];
  static int filePtr = 0;

  public static void main(String[] args) {
    System.out.println("コンパイル開始");
    try {
      String code[] = Files.readString(Paths.get("sample.txt")).replace("\t", "")
          .split(System.getProperty("line.separator"));
      bytes = new byte[code.length * 32];
      for (String string : code) {
        String opCode = string.split(" ")[0];
        String operands[] = string.split(" ")[1].split(",");
        if (opCode.equals("mov")) {
          mov(operands);
        } else if (opCode.equals("add")) {
          add(operands);
        } else if (opCode.equals("sub")) {
          sub(operands);
        } else if (opCode.equals("mul")) {
          mul(operands);
        } else if (opCode.equals("div")) {
          div(operands);
        } else if (opCode.equals("syscall")) {
          syscall(operands);
        } else if (opCode.equals("shr")) {
          shr(operands);
        } else if (opCode.equals("shl")) {
          shl(operands);
        } else if (opCode.equals("xor")) {
          xor(operands);
        } else if (opCode.equals("and")) {
          and(operands);
        } else if (opCode.equals("dw")) {
          dw(operands);
        } else if (opCode.equals("inc")) {
          inc(operands);
        } else if (opCode.equals("dec")) {
          dec(operands);
        } else if (opCode.equals("not")) {
          not(operands);
        } else {
          throw new UnknownCommandError("不明な命令:" + operands);
        }
      }
    } catch (Exception | MyMachineError e) {
      e.printStackTrace();
    }

    try {
      Files.write(Paths.get("sample.b"), bytes);
      System.out.println("コンパイル完了");
    } catch (Exception e) {
      System.err.println("ファイル書き込み中にエラーが発生しました:" + e);
    }
  }

  private static void not(String[] operands) {
    bytes[filePtr++] = Command.NOT;
    if (operands[0].charAt(0) == '[' && operands[0].charAt(operands[0].length() - 1) == ']') {
      bytes[filePtr++] = Command.TYPE_VARIABLE;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
      byte[] variable = operands[0].replace("[", "").replace("]", "").getBytes();
      for (byte b : variable) {
        bytes[filePtr++] = b;
      }
    } else if (operands[0].matches("^-?[0-9]+$")) {
      bytes[filePtr++] = Command.TYPE_MEMORY;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
    } else if (VirtualMachine.isRegister(operands[1])) {
      bytes[filePtr++] = Command.TYPE_REGISTER;
      writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[0]));
    }
  }

  private static void dec(String[] operands) {
    bytes[filePtr++] = Command.DEC;
    if (operands[0].charAt(0) == '[' && operands[0].charAt(operands[0].length() - 1) == ']') {
      bytes[filePtr++] = Command.TYPE_VARIABLE;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
      byte[] variable = operands[0].replace("[", "").replace("]", "").getBytes();
      for (byte b : variable) {
        bytes[filePtr++] = b;
      }
    } else if (operands[0].matches("^-?[0-9]+$")) {
      bytes[filePtr++] = Command.TYPE_MEMORY;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
    } else if (VirtualMachine.isRegister(operands[1])) {
      bytes[filePtr++] = Command.TYPE_REGISTER;
      writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[0]));
    }
  }

  private static void inc(String[] operands) {
    bytes[filePtr++] = Command.INC;
    if (operands[0].charAt(0) == '[' && operands[0].charAt(operands[0].length() - 1) == ']') {
      bytes[filePtr++] = Command.TYPE_VARIABLE;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
      byte[] variable = operands[0].replace("[", "").replace("]", "").getBytes();
      for (byte b : variable) {
        bytes[filePtr++] = b;
      }
    } else if (operands[0].matches("^-?[0-9]+$")) {
      bytes[filePtr++] = Command.TYPE_MEMORY;
      writeIntToByteArray(operands[0].replace("[", "").replace("]", "").length());
    } else if (VirtualMachine.isRegister(operands[0])) {
      bytes[filePtr++] = Command.TYPE_REGISTER;
      writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[0]));
    }
  }

  private static void mov(String[] operands) {
    bytes[filePtr++] = Command.MOV;

    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        int value = VirtualMachine.getRegisterNumber(operands[i]);
        writeIntToByteArray(value);
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        int value = Integer.parseInt(operands[i].replace("[", "").replace("]", ""));
        writeIntToByteArray(value);
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        int value = Integer.parseInt(operands[i]);
        writeIntToByteArray(value);
      } else {
        throw new UnknownCommandError("不明な型宣言" + operands[i] + "  index:" + operands[i]);
      }
    }
  }

  private static void writeIntToByteArray(int value) {
    bytes[filePtr++] = (byte) (value & 0xff);
    bytes[filePtr++] = (byte) ((value >> 8) & 0xff);
    bytes[filePtr++] = (byte) ((value >> 16) & 0xff);
    bytes[filePtr++] = (byte) ((value >> 24) & 0xff);
  }

  private static void add(String[] operands) {
    bytes[filePtr++] = Command.ADD;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void sub(String[] operands) {
    bytes[filePtr++] = Command.SUB;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void mul(String[] operands) {
    bytes[filePtr++] = Command.MUL;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void div(String[] operands) {
    bytes[filePtr++] = Command.DIV;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void shr(String[] operands) {
    bytes[filePtr++] = Command.SHR;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void shl(String[] operands) {
    bytes[filePtr++] = Command.SHL;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void xor(String[] operands) {
    bytes[filePtr++] = Command.XOR;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void and(String[] operands) {
    bytes[filePtr++] = Command.AND;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].charAt(0) == '[' && operands[i].charAt(operands[i].length() - 1) == ']') {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
    }
  }

  private static void syscall(String[] operands) {
    bytes[filePtr++] = Command.SYSCALL;
    for (int i = 0; i < 2; i++) {
      if (VirtualMachine.isRegister(operands[i])) {
        bytes[filePtr++] = Command.TYPE_REGISTER;
        writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[i]));
      } else if (operands[i].matches("^\\[[0-9]+\\]$")) {
        bytes[filePtr++] = Command.TYPE_MEMORY;
        writeIntToByteArray(Integer.parseInt(operands[i].replace("[", "").replace("]", "")));
      } else if (operands[i].matches("^-?[0-9]+$")) {
        bytes[filePtr++] = Command.TYPE_NUMBER;
        writeIntToByteArray(Integer.parseInt(operands[i]));
      }
      if (operands[i].matches("^\\[[a-z]+\\]$")) {
        bytes[filePtr++] = Command.TYPE_VARIABLE;
        writeIntToByteArray(operands[i].replace("[", "").replace("]", "").length());
        byte[] variable = operands[i].replace("[", "").replace("]", "").getBytes();
        for (int j = 0; j < variable.length; j++) {
          bytes[filePtr++] = variable[j];
        }
      }
    }
  }

  /*
   * dw命令は実行するときにhashmapに変数名をキーメモリアドレスを値として保存し、
   * 代入するときはhashmapから取得したメモリアドレスに対して書き込む
   */
  private static void dw(String[] operands) {
    bytes[filePtr++] = Command.DW;
    if (VirtualMachine.isRegister(operands[1])) {
      bytes[filePtr++] = Command.TYPE_REGISTER;
    } else if (operands[1].charAt(0) == '[' && operands[1].charAt(operands[1].length() - 1) == ']') {
      bytes[filePtr++] = Command.TYPE_MEMORY;
    } else if (operands[1].matches("^-?[0-9]+$")) {
      bytes[filePtr++] = Command.TYPE_NUMBER;
    } else {
      throw new UnknownCommandError("不明な値です。");
    }
    byte valueName[] = operands[0].getBytes();
    writeIntToByteArray(valueName.length);
    for (int i = 0; i < valueName.length; i++) {
      bytes[filePtr++] = valueName[i];
    }

    if (VirtualMachine.isRegister(operands[1])) {
      writeIntToByteArray(VirtualMachine.getRegisterNumber(operands[1]));
    } else if (operands[1].charAt(0) == '[' && operands[1].charAt(operands[1].length() - 1) == ']') {
      writeIntToByteArray(Integer.parseInt(operands[1].replace("[", "").replace("]", "")));
    } else if (operands[1].matches("^-?[0-9]+$")) {
      writeIntToByteArray(Integer.parseInt(operands[1]));
    } else {
      throw new UnknownCommandError("不明な値です。");
    }
  }
}

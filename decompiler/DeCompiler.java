package decompiler;

import java.nio.file.Files;
import java.nio.file.Paths;

import vm.Command;
import vm.VirtualMachine;

public class DeCompiler {
  public static int bytesToIntLE(byte[] bytes) {
    return (bytes[3] << 24) | ((bytes[2] & 0xFF) << 16) | ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
  }

  public static void main(String[] args) throws Exception {
    String code = "";
    byte bytes[] = Files.readAllBytes(Paths.get("sample.b"));
    for (int i = 0; i < bytes.length; i++) {
      if (bytes[i] == Command.MOV) {
        code += "mov ";
        if (bytes[i + 1] == Command.TYPE_MEMORY) {
          ++i;
          System.out.println("メモリ");
          code += "[" + bytes[i + 1] + "],";
          ++i;
          if (bytes[i + 1] == Command.TYPE_NUMBER) {
            i++;
            code += bytesToIntLE(new byte[] { bytes[i + 1], bytes[i + 2], bytes[i + 3], bytes[i + 4] });
            i += 4;
          }
        } else if (bytes[i + 1] == Command.TYPE_REGISTER) {
          ++i;
          System.out.println("レジスタ");
          code += VirtualMachine.getRegisterName(bytes[i + 1]) + ",";
          ++i;
          if (bytes[i + 1] == Command.TYPE_NUMBER) {
            i++;
            code += bytesToIntLE(new byte[] { bytes[i + 1], bytes[i + 2], bytes[i + 3], bytes[i + 4] });
            i += 4;
          } else if (bytes[i + 1] == Command.TYPE_MEMORY) {
            i++;
          }
        } else {
          System.out.println("不明:" + Integer.toHexString(bytes[i]));
        }

      } else if (bytes[i] == Command.ADD) {
        code += "add";
      }
    }
    System.out.println(code);
  }
}

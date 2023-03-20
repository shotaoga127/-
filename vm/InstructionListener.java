package vm;

import java.nio.file.*;

public class InstructionListener {
  public static byte[] bytes;
  public static int i = 0;

  public static int bytesToIntLE(byte[] bytes) {
    return (bytes[3] << 24) | ((bytes[2] & 0xFF) << 16) | ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
  }

  public static void main(String[] args) {
    try {
      bytes = Files.readAllBytes(Paths.get("sample.b"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (i = 0; i < bytes.length; i++) {
      if (bytes[i] == Command.MOV) {
        i++;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.movMemory();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.movRegister();
            break;
        }
      } else if (bytes[i] == Command.ADD) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.addMemory();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.addRegister();
            break;
        }
      } else if (bytes[i] == Command.SUB) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.subMemory();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.subRegister();
            break;
        }
      } else if (bytes[i] == Command.MUL) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.mulMemory();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.mulRegister();
            break;
        }
      } else if (bytes[i] == Command.DIV) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.divMemory();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.divRegister();
            break;
        }
      } else if (bytes[i] == Command.SHL) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.memoryShl();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.registerShl();
            break;
        }
      } else if (bytes[i] == Command.SHR) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.memoryShl();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.registerShl();
            break;
        }
      } else if (bytes[i] == Command.XOR) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.memoryXor();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.registerXor();
            break;
        }
      } else if (bytes[i] == Command.AND) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.memoryAnd();
            break;

          case Command.TYPE_REGISTER:
            InstructionExecution.registerAnd();
            break;
        }
      } else if (bytes[i] == Command.DW) {
        ++i;
        switch (bytes[i]) {
          case Command.TYPE_MEMORY:
            InstructionExecution.dwMemory();
            break;
        }
      } else if (bytes[i] == Command.SYSCALL) {
        ++i;
        InstructionExecution.syscall();
      }
    }
  }
}
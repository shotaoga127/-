package vm;

import java.util.Scanner;
import vm.exception.MyMachineError;
import vm.syscall.Syscalls;

public class InstructionExecution {
  public static void movMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, value);
  }

  public static void movRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, value);
  }

  public static void addMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) + value);
  }

  public static void addRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) + value);
  }

  public static void subMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) - value);
  }

  public static void subRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) - value);
  }

  public static void mulMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) * value);
  }

  public static void mulRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) * value);
  }

  public static void divMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) / value);
  }

  public static void divRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) / value);
  }

  public static void memoryShl() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) << value);
  }

  public static void registerShl() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) << value);
  }

  public static void shrMemory() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) >> value);
  }

  public static void shrRegister() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) >> value);
  }

  public static void memoryXor() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) ^ value);
  }

  public static void registerXor() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) ^ value);
  }

  public static void memoryAnd() {
    int memoryAddr = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.writeMemory(memoryAddr, VirtualMachine.readMemory(memoryAddr) & value);
  }

  public static void registerAnd() {
    int register = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
        InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
        InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    int value = 0x00;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      value = (int) VirtualMachine
          .readMemory(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      value = (int) VirtualMachine
          .getRegister(
              InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
                  InstructionListener.bytes[InstructionListener.i + 2],
                  InstructionListener.bytes[InstructionListener.i + 3],
                  InstructionListener.bytes[InstructionListener.i + 4] }));
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      value = InstructionListener.bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
    } else {
      throw new MyMachineError("指定できないタイプ");
    }
    VirtualMachine.setRegister(register, VirtualMachine.getRegister(register) & value);
  }

  public static void dwMemory() {
    int valueNameLength = InstructionListener
        .bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i + 1],
            InstructionListener.bytes[InstructionListener.i + 2], InstructionListener.bytes[InstructionListener.i + 3],
            InstructionListener.bytes[InstructionListener.i + 4] });
    InstructionListener.i += 4;
    InstructionListener.i++;
    byte valueName[] = new byte[valueNameLength];
    for (int i = 0; i < valueNameLength; i++) {
      valueName[i] = InstructionListener.bytes[InstructionListener.i++];
    }
    int memoryAddr = InstructionListener
        .bytesToIntLE(new byte[] { InstructionListener.bytes[InstructionListener.i++],
            InstructionListener.bytes[InstructionListener.i++], InstructionListener.bytes[InstructionListener.i++],
            InstructionListener.bytes[InstructionListener.i] });
    VirtualMachine.addValue(new String(valueName), VirtualMachine.readMemory(memoryAddr));
  }

  public static void syscall() {
    int syscall = -1;
    if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
      int number = InstructionListener.bytesToIntLE(new byte[] {
          InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2],
          InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      syscall = (int) VirtualMachine.readMemory(number);
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
      int number = InstructionListener.bytesToIntLE(new byte[] {
          InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2],
          InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      syscall = (int) VirtualMachine.getRegister(number);
      InstructionListener.i += 4;
    } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
      int number = InstructionListener.bytesToIntLE(new byte[] {
          InstructionListener.bytes[InstructionListener.i + 1],
          InstructionListener.bytes[InstructionListener.i + 2],
          InstructionListener.bytes[InstructionListener.i + 3],
          InstructionListener.bytes[InstructionListener.i + 4] });
      InstructionListener.i += 4;
      syscall = number;
    }
    InstructionListener.i++;
    if ((byte) syscall == Syscalls.WRITE) {
      Object value = null;
      if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
        int number = InstructionListener.bytesToIntLE(new byte[] {
            InstructionListener.bytes[InstructionListener.i + 1],
            InstructionListener.bytes[InstructionListener.i + 2],
            InstructionListener.bytes[InstructionListener.i + 3],
            InstructionListener.bytes[InstructionListener.i + 4] });
        InstructionListener.i += 4;
        value = VirtualMachine.readMemory(number);
      } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
        int number = InstructionListener.bytesToIntLE(new byte[] {
            InstructionListener.bytes[InstructionListener.i + 1],
            InstructionListener.bytes[InstructionListener.i + 2],
            InstructionListener.bytes[InstructionListener.i + 3],
            InstructionListener.bytes[InstructionListener.i + 4] });
        InstructionListener.i += 4;
        value = VirtualMachine.getRegister(number);
      } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_NUMBER) {
        int number = InstructionListener.bytesToIntLE(new byte[] {
            InstructionListener.bytes[InstructionListener.i + 1],
            InstructionListener.bytes[InstructionListener.i + 2],
            InstructionListener.bytes[InstructionListener.i + 3],
            InstructionListener.bytes[InstructionListener.i + 4] });
        InstructionListener.i += 4;
        value = number;
      }
      System.out.println(value);
    } else if ((byte) syscall == Syscalls.INPUT) {
      try (Scanner scanner = new Scanner(System.in)) {
        if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_MEMORY) {
          int number = InstructionListener.bytesToIntLE(new byte[] {
              InstructionListener.bytes[InstructionListener.i + 1],
              InstructionListener.bytes[InstructionListener.i + 2],
              InstructionListener.bytes[InstructionListener.i + 3],
              InstructionListener.bytes[InstructionListener.i + 4] });
          InstructionListener.i += 4;
          VirtualMachine.writeMemory(number, scanner.nextInt());
        } else if (InstructionListener.bytes[InstructionListener.i] == Command.TYPE_REGISTER) {
          int number = InstructionListener.bytesToIntLE(new byte[] {
              InstructionListener.bytes[InstructionListener.i + 1],
              InstructionListener.bytes[InstructionListener.i + 2],
              InstructionListener.bytes[InstructionListener.i + 3],
              InstructionListener.bytes[InstructionListener.i + 4] });
          InstructionListener.i += 4;
          if (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            VirtualMachine.setRegister(number, value);
          } else {
            System.out.println("エラー:整数を入力してください。");
          }
        } else {
          System.out.println("不明なシステムコール");
        }
      }
    }
  }

}
package vm.syscall;

import vm.VirtualMachine;

public class SyscallRun {
  public static void writeMemory(int memory) {
    System.out.println(VirtualMachine.readMemory(memory));
  }

  public static void writeRegister(int register) {
    System.out.println(VirtualMachine.getRegister(register));
  }

  public static void write(Object o) {
    System.out.println(o);
  }
}

package vm.exception;

/** VirtualMachineに対するエラークラス */
public class MyMachineError extends Error {

  /**
   * メッセージを設定する
   * 
   * @param msg メッセージ
   */
  public MyMachineError(String msg) {
    super(msg);
  }

  public MyMachineError() {
    super();
  }
}

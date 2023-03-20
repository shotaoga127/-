package vm.exception;

/** 不明な命令に対するエラー */
public class UnknownCommandError extends MyMachineError {

  /**
   * Messageを設定する
   * 
   * @param msg メッセージ
   */
  public UnknownCommandError(String msg) {
    super(msg);
  }

  public UnknownCommandError() {
    super();
  }
}

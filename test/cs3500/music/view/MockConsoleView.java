package cs3500.music.view;

import cs3500.music.model.ViewModel;
import cs3500.music.view.ConsoleView;

/**
 * A mock console view for testing purposes: its functionality is the same, but it prints to a
 * StringBuilder.
 */
public class MockConsoleView extends ConsoleView {
  private StringBuffer sb;
  private ViewModel vm;

  public MockConsoleView(StringBuffer sb, ViewModel vm) {
    this.sb = sb;
    this.vm = vm;
  }

  @Override
  public void show(int beat) {
    sb.append(vm.toString());
  }
}

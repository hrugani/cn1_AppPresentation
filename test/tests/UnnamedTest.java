package tests;

import com.codename1.testing.AbstractTest;

import com.codename1.ui.Display;

public class UnnamedTest extends AbstractTest {
    public boolean runTest() throws Exception {
        screenshotTest("UnnamedTest_1");
        screenshotTest("UnnamedTest_2");
        screenshotTest("UnnamedTest_3");
        screenshotTest("UnnamedTest_4");
        screenshotTest("UnnamedTest_5");
        waitForFormTitle("Clientes");
        return true;
    }
}

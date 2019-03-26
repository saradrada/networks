package runtimeExec;

import java.io.IOException;

public class RuntimeExecExcel {

    public static void main(String args[])
            throws IOException
        {
            Runtime.getRuntime().exec("cmd /c start excel.exe");
        }

}

package Main;

public class State {
    public final int preprocess = 0;
    public final int declaration = 1;
    public final int statement = 2;
    public final int IFCondition = 3;
    public final int ELSEIFCondition = 4;
    public final int ELSE = 5;
    public final int FORBlock = 6;
    public final int WHILEBlock = 7;
    public final int DOPoint = 8;
    public final int Brace_OP = 9;
    public final int Brace_ED = 10;
}

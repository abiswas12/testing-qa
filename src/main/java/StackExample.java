import java.util.Stack;

public class StackExample {
    private Stack<String> stack;
    public StackExample() {
        stack = new Stack<String>();
    }
    public int getSize(){
        return stack.size();
    }
    public void addString(String newString){
        stack.push(newString);
    }
    public void deleteLast(){
        stack.pop();
    }
}

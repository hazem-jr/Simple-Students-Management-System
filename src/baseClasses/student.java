
package baseClasses;

public class student {
String code , name ;

    public student(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public student() {
    }

    public student(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}

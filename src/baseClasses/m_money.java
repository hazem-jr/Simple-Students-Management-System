package baseClasses;

import javafx.scene.control.CheckBox;

public class m_money {

    String code, name, money;
    CheckBox check;

    public m_money() {
    }

    public m_money(String code, String name, String money) {
        this.code = code;
        this.name = name;
        this.money = money;
    }

    public m_money(String code, String name) {
        this.code = code;
        this.name = name;
        this.check = new CheckBox();
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }
}

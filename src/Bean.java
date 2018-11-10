class Bean{
    private Boolean flag; // true为按钮控制状态
    private Boolean button; // true为speedUp按钮或turnLeft按钮

    void setFlag(Boolean flag) {
        this.flag = flag;
    }

    void setButton(Boolean button) {
        this.button = button;
    }

    Boolean getFlag() {
        return flag;
    }

    Boolean getButton() {
        return button;
    }
}
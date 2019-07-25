package com.example.jaankaar;

import java.io.Serializable;

public class DSGN  implements Serializable {
    public DSGN(String title, String add) {
        this.Title = title;
        this.add = add;
    }

    String Title;
    String add;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}

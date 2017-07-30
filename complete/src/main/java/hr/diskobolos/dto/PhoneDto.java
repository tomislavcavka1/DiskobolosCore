/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.dto;

import java.util.List;

/**
 *
 * @author Tomislav Cavka
 */
public class PhoneDto {

    private String category;

    private List<Value> data;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Value> getData() {
        return data;
    }

    public void setData(List<Value> data) {
        this.data = data;
    }

    public static class Value {

        private Integer id;
        private String text;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        private void setText(String text) {
            this.text = text;
        }
    }
}

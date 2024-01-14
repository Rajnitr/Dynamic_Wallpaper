package com.example.myscreensaver

class Utils {

    companion object {
        fun getCategories(): List<String> {
            var list= mutableListOf<String>()
            list.add("All")
            list.add("Birds")
            list.add("Nature")
            list.add("Desert")
            list.add("Car")
            list.add("Tiger")
            list.add("Birds eye view")
            return list.toList()
        }
    }
}
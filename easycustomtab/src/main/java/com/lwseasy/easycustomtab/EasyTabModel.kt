package com.lwseasy.easycustomtab

/**
 * Created by Low Wei Siong on 19/01/2021
 **/

data class EasyTabModel(var title: String?, val image: Int?) {

    constructor(title: String) : this(title, null)

    constructor(image: Int) : this(null, image)
}
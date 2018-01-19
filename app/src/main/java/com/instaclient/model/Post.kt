package com.instaclient.model


data class Post(val type: String, var user: User, var images: Images, var created_time: String)
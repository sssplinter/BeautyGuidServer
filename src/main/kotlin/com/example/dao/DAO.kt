package com.example.dao

import com.example.database.Post
import io.ktor.utils.io.core.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

interface DAOInterface : Closeable {
    fun init()
    fun createPost(title: String, description: String)
    fun updatePost(id: Int, title: String, description: String)
//    fun deletePost(id: Int)
    fun getPost(id: Int): Post?
    fun getAllPost(): List<Post>
}

class PostsDao(val db : Database) : DAOInterface{
    override fun init() = transaction(db){
        SchemaUtils.create(Posts)
    }

    override fun createPost(title: String, description: String) = transaction(db){
        Posts.insert {
            it[Posts.title] = title
            it[Posts.description] = description
        }
        Unit
    }

    override fun updatePost(id: Int, title: String, description: String) = transaction(db){
        Posts.update({ Posts.id eq id }) {
            it[Posts.title] = title
            it[Posts.description] = description
        }
        Unit
    }

//    override fun deletePost(id: Int) = transaction(db){
//        Posts.deleteWhere { Posts.id eq id }
////        Unit
//    }

    override fun getPost(id: Int): Post? = transaction(db) {
        Posts.select { Posts.id eq id }.map {
            Post(
                it[Posts.id], it[Posts.title], it[Posts.description]
            )
        }.singleOrNull()
    }

    override fun getAllPost(): List<Post> = transaction(db) {
        Posts.selectAll().map {
            Post(
                it[Posts.id], it[Posts.title], it[Posts.description]
            )
        }
    }

    override fun close() {}

}
package com.example.sample.registration

import com.example.data.db.dao.UserCredentialsDao
import com.example.data.db.dao.UsersDao
import com.example.data.models.User
import com.example.data.models.UserCredentials
import com.example.routing.login.LoginRequest
import com.example.routing.login.LoginResponse
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting(
    hashingService: HashingService,
    userDataSource: UsersDao,
    userCredentialsDao: UserCredentialsDao,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    //TODO можно потом разбить на отдельные Route методы
    routing {
//        post("/register") {
//            val receive = call.receive(RegisterReceiveRemote::class)

//            if (!receive.email.isValidEmail()) {
//                call.respond(HttpStatusCode.BadRequest, "Incorrect email")
//            }
//
//            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
//                call.respond(HttpStatusCode.Conflict, "User already exists")
//            }
//
//            val generatedToken = "Test token"//java.util.UUID.randomUUID().toString()
//            InMemoryCache.userList.add(receive)
//            InMemoryCache.tokenList.add(TokenCache(login = receive.login, token = generatedToken))

//            call.respond(RegisterResponseRemote(token = generatedToken))
//        }

//        post("register") {
//            val request = runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: run {
//                call.respond(HttpStatusCode.BadRequest)
//                return@post
//            }
//            val fieldsBlank = request.username.isBlank() || request.password.isBlank()
//            val isPasswordShort = request.password.length < 8
//            if (fieldsBlank || isPasswordShort) {
//                call.respond(HttpStatusCode.Conflict, "Error message")
//                return@post
//            }
//
//            val saltedHash = hashingService.generateSaltedHash(request.password)
//            val user = User(
//                username = request.username,
//            )
//            val userCredentials = UserCredentials(
//                password = saltedHash.hash,
//                salt = saltedHash.salt
//            )
//            //проверка на то что норм удалось добавить в бд
//            val wasAcknowledged = userDataSource.insertUser(user)
//            if (!wasAcknowledged) {
//                call.respond(HttpStatusCode.Conflict)
//                return@post
//            }
//
//            call.respond(HttpStatusCode.OK)
//        }

//        post("login") {
//            val request = runCatching { call.receiveNullable<LoginRequest>() }.getOrNull() ?: run {
//                call.respond(HttpStatusCode.BadRequest)
//                return@post
//            }
//
//            val user = userDataSource.fetchUserByUsername(request.username)
//            val userCredentials = userCredentialsDao.fetchCredentialsByUsername(request.username)
//            if (userCredentials == null || user == null) {
//                call.respond(HttpStatusCode.Conflict, "Incorrect username")
//                return@post
//            }
//
//            val isValidPassword = hashingService.verify(
//                value = request.password,
//                saltedHash = SaltedHash(
//                    hash = userCredentials.password,
//                    salt = userCredentials.salt
//                )
//            )
//            if (!isValidPassword) {
//                call.respond(HttpStatusCode.Conflict, "Incorrect password")
//                return@post
//            }
//
//            val token = tokenService.generate(
//                config = tokenConfig,
//                TokenClaim(
//                    name = "userId",
//                    value = user.id.toString()
//                ),
//            )
//
//            call.respond(
//                status = HttpStatusCode.OK,
//                message = LoginResponse(
//                    token = token
//                )
//            )
//        }


    }
}
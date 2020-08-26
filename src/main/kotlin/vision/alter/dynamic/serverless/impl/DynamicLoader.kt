package ru.rshb.intech.umqa.service.serverless.impl

interface DynamicLoader {
    fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*>
}

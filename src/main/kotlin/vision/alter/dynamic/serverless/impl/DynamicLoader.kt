package vision.alter.dynamic.serverless.impl

interface DynamicLoader {
    fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*>
}

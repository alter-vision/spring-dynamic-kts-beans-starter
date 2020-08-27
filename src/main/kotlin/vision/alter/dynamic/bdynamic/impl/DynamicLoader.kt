package vision.alter.dynamic.bdynamic.impl

interface DynamicLoader {
    fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*>
}

package vision.alter.dynamic.serverless.impl


class DynamicKtsClassLoader : ClassLoader(), DynamicLoader {
    override fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*> =
        this.defineClass(path, classData, 0, classData.size)
}
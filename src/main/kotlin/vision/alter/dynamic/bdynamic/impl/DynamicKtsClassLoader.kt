package vision.alter.dynamic.bdynamic.impl


class DynamicKtsClassLoader : ClassLoader(), DynamicLoader {
    override fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*> =
        this.defineClass(path, classData, 0, classData.size)
}
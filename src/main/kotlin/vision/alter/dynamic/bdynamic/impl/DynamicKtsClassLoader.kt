package vision.alter.dynamic.bdynamic.impl

import java.io.InputStream


class DynamicKtsClassLoader(
    private val parentClassLoader: ClassLoader = Thread.currentThread().contextClassLoader
) : ClassLoader(parentClassLoader), DynamicLoader {

    init {
        Thread.currentThread().contextClassLoader = this
    }


    override fun defineClassFromByteCode(classData: ByteArray, path: String): Class<*> =
        this.defineClass(path, classData, 0, classData.size)

    override fun getResourceAsStream(name: String?): InputStream? {
        return parentClassLoader.getResourceAsStream(name)
    }
}

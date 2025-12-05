import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun createZip(sourceDirPath: String, zipPath: String) {
    val sourceDir = File(sourceDirPath)
    if (!sourceDir.exists()) {
        println("Нет такого каталога: $sourceDirPath")
        return
    }

    ZipOutputStream(FileOutputStream(zipPath)).use { zipOut ->
        addToZip(sourceDir, sourceDir.absolutePath, zipOut)
    }
}

private fun addToZip(file: File, base: String, zipOut: ZipOutputStream) {
    val items = file.listFiles() ?: return

    for (item in items) {
        if (item.isDirectory) {
            addToZip(item, base, zipOut)
        } else {
            val relative = item.absolutePath.removePrefix(base + File.separator)
            if (!relative.endsWith(".txt") && !relative.endsWith(".log")) {
                continue
            }

            println("Добавляю файл: $relative (${item.length()} байт)")

            FileInputStream(item).use { fis ->
                val entry = ZipEntry(relative.replace("\\", "/"))
                zipOut.putNextEntry(entry)

                val buffer = ByteArray(1024)
                var read = fis.read(buffer)
                while (read > 0) {
                    zipOut.write(buffer, 0, read)
                    read = fis.read(buffer)
                }

                zipOut.closeEntry()
            }
        }
    }
}
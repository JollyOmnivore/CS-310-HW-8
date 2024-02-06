import java.io.File

data class Color(val name: String, val red: Int, val green: Int, val blue: Int)

fun readFileName(): String {
    print("Enter the file name: ")
    val inputFileName = readLine()
    if(inputFileName != null){
        return inputFileName
    }
    else{
        error("User didn't enter file name or entered file name is null")
    }
}

fun readFile(fileName: String): Array<Color?> {
    val file = File(fileName)

    if (!file.exists()) {
        println("File not found: $fileName")
        return arrayOfNulls(0)
    }

    val text = file.readLines()

    val colors = arrayOfNulls<Color>(text.size)

    for ((i, line) in text.withIndex()) {
        colors[i] = colorToString(line)
    }

    return colors
}

fun colorToString(line: String): Color {
    val parts = line.split(",")
    if (parts.size == 4) {
        val name = parts[0]
        val red = parts[1].toInt()
        val green = parts[2].toInt()
        val blue = parts[3].toInt()
        return Color(name, red, green, blue)
    }
    error("test file contained null variables or was input incorrectly ")
}

fun findAndPrint(colors: Array<Color?>) {
    for (i in colors.indices) {
        val color1 = colors[i]!!
        var x = i + 1
        while (x < colors.size) {
            val color2 = colors[x]!!

            if (color1.red == color2.red && color1.green == color2.green && color1.blue == color2.blue) {

                if (color1.name != color2.name) {
                    println("===Match found!===")
                    printResults(color1)
                    printResults(color2)
                }
            }
            x++
        }

    }
}

fun printResults(color: Color) {
    print("Name: ")
    print(color.name)
    print(" Red: (${color.red}, Green: ${color.green}, Blue: ${color.blue})\n")
}

fun main() {
    val fileName = readFileName()
    val colors = readFile(fileName)
    findAndPrint(colors)
}
import ru.sber.filesystem.VFilesystem
import ru.sber.filesystem.VPath
import java.io.IOException
import java.net.ServerSocket
import java.nio.channels.SocketChannel

/**
 * A basic and very limited implementation of a file server that responds to GET
 * requests from HTTP clients.
 */
class FileServer {

    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @param fs     A proxy filesystem to serve files from. See the VFilesystem
     *               class for more detailed documentation of its usage.
     * @throws IOException If an I/O error is detected on the server. This
     *                     should be a fatal error, your file server
     *                     implementation is not expected to ever throw
     *                     IOExceptions during normal operation.
     */
    @Throws(IOException::class)
    fun run(socket: ServerSocket, fs: VFilesystem) {

        /**
         * Enter a spin loop for handling client requests to the provided
         * ServerSocket object.
         */
        socket.use {
            while (true) {
                val s = it.accept()


                val reader = s.getInputStream().bufferedReader()
                val clientRequest = reader.readLine().split(" ")
                val path = VPath(clientRequest[1])
                val info = fs.readFile(path)


                val writer = s.getOutputStream().bufferedWriter()
                if(info.isNullOrEmpty()){
                    writer.write( "HTTP/1.0 404 Not Found\r\n")
                    writer.write("Server: FileServer\r\n")
                    writer.write("\r\n")
                }
                else{
                    writer.write("HTTP/1.0 200 OK\r\n")
                    writer.write("Server: FileServer\r\n")
                    writer.write("\r\n")
                    writer.write("${info}\r\n")

                }
                writer.flush()
                writer.close()
            }
        }
    }
}
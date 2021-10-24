package kotlin.com.example.demo.filter


import java.util.*
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(
    urlPatterns = ["/api/*", "/app/*"]
)
class LogginingFilter: HttpFilter() {

    private lateinit var context: ServletContext

    override fun init(filterConfig: FilterConfig) {
        this.context = filterConfig.servletContext
        this.context.log("AuthentificationFilter")
    }

    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        val cookies = request!!.cookies

        if(cookies == null) {
            this.context.log("No cookies found")
            this.context.log("Unauthorized access request")
            response!!.sendRedirect("/login")
        } else {
            val currentTime = Calendar.getInstance().timeInMillis.toString()
            for(cookie in cookies) {
                if(cookie.name != "auth") {
                    this.context.log("Wrong Cookie name:: " + cookie.name)
                    response!!.sendRedirect("/login")
                } else if(cookie.value >= currentTime) {
                    this.context.log("Wrong Cookie value:: " + cookie.value + " is not less than " + currentTime)
                    response!!.sendRedirect("/login")
                } else {
                    chain!!.doFilter(request, response)
                }
            }
        }
    }
}
package com.gestao.agricola.configurations;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsFilter implements Filter {
public class CorsFilter {

    private String origemPermitida = "http://localhost:4200";

    /*@Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", origemPermitida);
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if("OPTIONS".equals(request.getMethod()) && origemPermitida.equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "360");

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }*/
}

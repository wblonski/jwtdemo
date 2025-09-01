package pl.wblo.jwtdemo.app.config;

//@Component
public class JwtAuthenticationFilter_old
//        extends OncePerRequestFilter
{
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//    {
//        System.out.println("JwtAuthenticationFilter initial block is running");
//    }
//    public JwtAuthenticationFilter_old(JwtService jwtService, UserDetailsService userDetailsService) {
//        System.out.println("JwtAuthenticationFilter constructor is running");
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        System.out.println("doFilterInternal is running");
////        if (request.getServletPath().contains("/api/v1/auth/register")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////        final String authHeader = request.getHeader("Authorization");
////        final String jwt;
////        final String userEmail;
////        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////        jwt = authHeader.substring(7);
////        userEmail = jwtService.extractUsername(jwt);
////        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
////            if (jwtService.isTokenValid(jwt, userDetails)) {
////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
////                        userDetails,
////                        null,
////                        userDetails.getAuthorities()
////                );
////                authToken.setDetails(
////                        new WebAuthenticationDetailsSource().buildDetails(request)
////                );
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
////        }
//        filterChain.doFilter(request, response);
//    }
}

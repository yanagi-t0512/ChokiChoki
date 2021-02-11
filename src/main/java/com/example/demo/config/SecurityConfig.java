package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// パスワード暗号化
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティ設定を無視するパスを指定
		web.ignoring().antMatchers("/js/**", "/css/**", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// 認証リクエストの設定
			.authorizeRequests()
				// アクセス可能
				.antMatchers("/", "/index", "/login", "/signup").permitAll()
				// ADMINユーザーだけアクセス可能
				//.antMatchers("/admin/**").hasRole(Role.ADMIN.name())
				// 直リンク禁止
				.anyRequest().authenticated()
				.and()

			// ログイン処理
			.formLogin()
				// ログイン時のURLを指定
				.loginPage("/login")
				// 認証後にリダイレクトする場所を指定
				.defaultSuccessUrl("/reservation")
				.and()
			.logout()
				// ログアウト時のURLを指定
				.logoutRequestMatcher(new AntPathRequestMatcher("/"))
				.and()
			.rememberMe();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
//		.inMemoryAuthentication()
//		.withUser("user")
//		.password(passwordEncoder().encode("12345"))
//		.authorities("ROLE_GENERAL");
	}
}

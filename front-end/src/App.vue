<template>
  <div id="app">
    <v-app id="inspire">
      <v-app id="inspire">
        <v-navigation-drawer v-model="drawer" app clipped>
          <v-list dense>
            <router-link :to="{ name: 'home' }" class="router-button">
              <v-list-item link>
                <v-list-item-action>
                  <v-icon>mdi-home</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>Home</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </router-link>
            <router-link
              v-if="!isLogin"
              :to="{ name: 'login' }"
              class="router-button"
            >
              <v-list-item link>
                <v-list-item-action>
                  <v-icon>mdi-account</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>Login</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </router-link>
            <v-list-item v-else @click="logout">
              <v-list-item-action>
                <v-icon>mdi-account-off-outline</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Logout</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <router-link
              v-if="!isLogin"
              :to="{ name: 'login', params: { getAccountPort: true } }"
              class="router-button"
            >
              <v-list-item link>
                <v-list-item-action>
                  <v-icon>mdi-human-greeting</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>MyPortfolio</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </router-link>
            <router-link
              v-else
              :to="{
                name: 'view-portfolio',
                params: { nickname: userInfo.nickname }
              }"
              class="router-button"
            >
              <v-list-item link>
                <v-list-item-action>
                  <v-icon>mdi-human-greeting</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>MyPortfolio</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </router-link>
            <v-list-item link>
              <v-list-item-action>
                <v-icon>mdi-cog</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Profile</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>

        <v-app-bar app color="indigo" dark>
          <v-app-bar-nav-icon
            @click.stop="drawer = !drawer"
          ></v-app-bar-nav-icon>
          <v-toolbar-title>Portfolio For Developers</v-toolbar-title>
        </v-app-bar>

        <v-main>
          <v-container class="fill-height" fluid>
            <v-row align="center" justify="center">
              <router-view />
            </v-row>
          </v-container>
        </v-main>
        <v-footer color="indigo" app>
          <span class="white--text"
            >&copy; {{ new Date().getFullYear() }} vividswan</span
          >
        </v-footer>
      </v-app>
    </v-app>
  </div>
</template>
<script>
import { mapState, mapMutations } from "vuex"
export default {
  name: "App",

  data: () => ({
    drawer: null
  }),
  computed: {
    ...mapState(["isLogin", "userInfo"])
  },
  methods: {
    ...mapMutations(["logout"])
  }
}
</script>
<style scoped>
.router-button {
  text-decoration: none;
}
</style>

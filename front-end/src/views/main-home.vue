<template>
  <div id="home">
    <transition name="fadeOne">
      <div v-show="show" class="container">
        <p class="One">Portfolio</p>
      </div>
    </transition>
    <transition name="fadeTwo">
      <div v-show="show" class="container">
        <p class="Two">Portfolio For Developers</p>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "Home",
  data: () => ({
    show: false
  }),
  created() {
    setTimeout(this._showFunc, 100)
  },
  methods: {
    _showFunc() {
      this.show = true
    }
  }
}
</script>
<style scoped>
html {
  margin: 0;
  padding: 0;
  font-family: "Arial Black", Gadget, sans-serif;
}

.container {
  clip-path: polygon(0% 0%, 100% 0%, 100% 100%, 0% 100%);
}

p {
  text-align: center;
  font-size: 50px;
  overflow: hidden;
  color: #3f51b5;
  margin: 1px;
}

.Two {
  font-size: 18px;
  font-family: Georgia, serif;
}

.fadeOne-enter-active,
.fadeOne-leave-active {
  transition: all 0.5s ease-out;
}
.fadeOne-enter,
.fadeOne-leave-to {
  clip-path: polygon(0% 0%, 100% 0%, 100% 0%, 0% 0%);
  transform: translate(0%, 100%);
}

.fadeTwo-enter-active,
.fadeTwo-leave-active {
  transition: all 0.5s ease-out;
}
.fadeTwo-enter,
.fadeTwo-leave-to {
  clip-path: polygon(0% 100%, 100% 100%, 0% 100%, 0% 100%);
  transform: translate(0%, -100%);
}
</style>

import searchCodelist from "./search/searchCodelist.vue";
import codelistVisualizer from "./visualizer/codelistVisualizer.vue";
import codelistEditor from "./editor/codelistEditor.vue";
import Vue from 'vue';

export const eventBus = new Vue();

export default {
    name: "codelistPage",

    components: {
        searchCodelist,
        codelistVisualizer,
        codelistEditor
    }
}
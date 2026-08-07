package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;
// Fix: don't force an is-a relationship that doesn't hold behaviorally.
// Square and Rectangle both implement a Shape contract instead of one extending the other.
// Neither can be passed to code expecting to independently mutate width/height —
// because neither exposes that operation. Immutability sidesteps the LSP trap entirely.

public interface Shape {
    int area();
}

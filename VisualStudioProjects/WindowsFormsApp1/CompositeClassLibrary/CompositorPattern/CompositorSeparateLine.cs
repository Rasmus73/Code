using CompositeClassLibrary.CompositePattern;

namespace CompositeClassLibrary.CompositorPattern
{
    public class CompositorSeparateLine : Compositor
    {
        private int _width;

        public CompositorSeparateLine(int displayWidth)
        {
            _width = displayWidth;
        }

        internal override void Compose(Composite composite)
        {
            GroupByTypeInComposites(composite);

            PositionCalculation pc = new PositionCalculation(composite);
            pc.SetPositions(_width);
        }

        internal void GroupByTypeInComposites(Composite composite)
        {
            composite.SortComponentsByType();

            Composite composedRoot = null;
            Composite currentLine = null;
            IComponent prevComponent = null;
            int cnt = 0;
            foreach (IComponent component in composite)
            {
                if (prevComponent?.GetType() != component.GetType())
                {
                    if (composedRoot == null)
                    {
                        composedRoot = new Composite("ComposedRoot");
                    }
                    else
                    {
                        composedRoot.Add(currentLine);
                    }
                    currentLine = new Composite("CompositeLine_" + cnt);
                    cnt++;
                }

                currentLine.Add(component);
                prevComponent = component;
            }

            composedRoot.Add(currentLine);

            composite.Clear(); // TODO: I do not like this... refactor/change method.
            composite.Name = composedRoot.Name;
            foreach (var i in composedRoot)
            {
                composite.Add(i);
            }
        }
    }
}
